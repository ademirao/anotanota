package org.anotanota.pipeline.main;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import org.anotanota.pipeline.AnotanotaPipeline.FullPipeline;
import org.anotanota.pipeline.AnotanotaPipelineModule;
import org.anotanota.pipeline.InputFileProducer;
import org.anotanota.pipeline.OCR;
import org.anotanota.pipeline.ReceiptItemsProducer;
import org.anotanota.pipeline.framework.Pipeline;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.exec.DefaultExecutor;
import org.dagger.scope.ScopeModule;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import dagger.Module;
import dagger.Provides;

public class Main {

	@SuppressWarnings("static-access")
	public static final Option inputFile = OptionBuilder.hasArgs()
	.withArgName("file_name")
	.withDescription("The receipt file to process")
	.create("receipt");

	@Module(includes={AnotanotaPipelineModule.class, ScopeModule.class}, injects = { Main.class })
	public static class MainModule {
		String [] args;
		public MainModule(String [] args) {
			this.args = args;
		}
		@Provides
		Options getOptions() {
			Options opts = new Options();
			opts.addOption(inputFile);
			return opts;
		}

		@Provides
		ListeningExecutorService getService() {
			return MoreExecutors.sameThreadExecutor();
		}

		@Provides
		CommandLine getCm(Options options) {
			try {
				return new PosixParser().parse(options, args);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}	
		}

		@Provides
		Pipeline getPipeline(@FullPipeline Pipeline pipeline, CommandLine cml) {
			pipeline.addModule(new InputFileProducer(new File(cml.getOptionValue(inputFile.getOpt()))));
			return pipeline;
		}

		@Provides
		OCR getOcr() {
			return new OCR() {
				@Override
				public String getUTF8Text(File file) {
					File tmpFile = null;
					try {
						tmpFile = File.createTempFile("anotanota", ".txt");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						throw new RuntimeException(e1);
					}
					DefaultExecutor exec = new DefaultExecutor();
					Map<String, String> env = ImmutableMap.of("TESSDATA_PREFIX",
							"/home/ademirao/git_repos/anotanota/training/working" );
					String fullPath = tmpFile.getAbsolutePath();
					String pathNoExtension = fullPath.substring(0, fullPath.length() - ".txt".length());
					org.apache.commons.exec.CommandLine cmd = 
							org.apache.commons.exec.CommandLine.parse(
									"tesseract " + file.getAbsolutePath() + " " + pathNoExtension + " -l nota ");

					try {
						System.out.println(cmd + " " + tmpFile.getAbsolutePath());
						exec.execute(cmd, env);

						return com.google.common.io.Files.toString(tmpFile, Charsets.UTF_8);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			};
		}
	}

	@Inject
	Pipeline pipeline;

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Main m = new Main();
		org.dagger.scope.Scope.newRootScope(new MainModule(args)).getGraph().inject(m);
		ReceiptItemsProducer.Result r = new ReceiptItemsProducer.Result();
		m.pipeline.produceInto(r).get();
		System.out.println(r.result);
	}
}
