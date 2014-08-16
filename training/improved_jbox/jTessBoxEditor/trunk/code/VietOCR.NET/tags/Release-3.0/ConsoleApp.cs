﻿using System;
using System.Collections.Generic;
using System.Text;
using System.IO;
using System.Drawing;
using VietOCR.NET.Postprocessing;
using VietOCR.NET.Utilities;

namespace VietOCR.NET
{
    class ConsoleApp
    {
        public static void Main(string[] args)
        {
            new ConsoleApp().PerformOCR(args);
        }

        void PerformOCR(string[] args)
        {
            try
            {
                if (args[0] == "-?" || args[0] == "-help" || args.Length == 1 || args.Length == 3)
                {
                    Console.WriteLine("Usage: vietocr imagefile outputfile [-l langcode]");
                    return;
                }
                FileInfo imageFile = new FileInfo(args[0]);
                FileInfo outputFile = new FileInfo(args[1]);

                if (!imageFile.Exists)
                {
                    Console.WriteLine("Input file does not exist.");
                    return;
                }

                string curLangCode;

                if (args.Length == 2)
                {
                    curLangCode = "eng"; //default language
                }
                else
                {
                    curLangCode = args[3];
                }

                //IList<Image> imageList = ImageIOHelper.GetImageList(imageFile);

                OCR<string> ocrEngine = new OCRFiles();
                IList<string> files = new List<string>();
                files.Add(imageFile.FullName);
                string result = ocrEngine.RecognizeText(files, curLangCode);

                // postprocess to correct common OCR errors
                result = Processor.PostProcess(result, curLangCode);
                // correct common errors caused by OCR
                result = TextUtilities.CorrectOCRErrors(result);
                // correct letter cases
                result = TextUtilities.CorrectLetterCases(result);

                using (StreamWriter sw = new StreamWriter(outputFile.FullName + ".txt", false, new System.Text.UTF8Encoding()))
                {
                    sw.Write(result);
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("Error: " + e.Message);
            }
        }
    }
}