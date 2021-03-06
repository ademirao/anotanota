﻿namespace VietOCR.NET
{
    partial class SplitPdfDialog
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(SplitPdfDialog));
            this.buttonSplit = new System.Windows.Forms.Button();
            this.buttonCancel = new System.Windows.Forms.Button();
            this.textBoxFrom = new NumericTextBox();
            this.textBoxTo = new NumericTextBox();
            this.radioButtonPages = new System.Windows.Forms.RadioButton();
            this.labelFrom = new System.Windows.Forms.Label();
            this.labelTo = new System.Windows.Forms.Label();
            this.radioButtonFiles = new System.Windows.Forms.RadioButton();
            this.textBoxNumOfPages = new NumericTextBox();
            this.labelNumOfPages = new System.Windows.Forms.Label();
            this.textBoxInput = new System.Windows.Forms.TextBox();
            this.textBoxOutput = new System.Windows.Forms.TextBox();
            this.buttonBrowseInput = new System.Windows.Forms.Button();
            this.buttonBrowseOutput = new System.Windows.Forms.Button();
            this.labelInput = new System.Windows.Forms.Label();
            this.labelOutput = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // buttonSplit
            // 
            this.buttonSplit.DialogResult = System.Windows.Forms.DialogResult.OK;
            resources.ApplyResources(this.buttonSplit, "buttonSplit");
            this.buttonSplit.Name = "buttonSplit";
            this.buttonSplit.UseVisualStyleBackColor = true;
            this.buttonSplit.Click += new System.EventHandler(this.buttonSplit_Click);
            // 
            // buttonCancel
            // 
            this.buttonCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            resources.ApplyResources(this.buttonCancel, "buttonCancel");
            this.buttonCancel.Name = "buttonCancel";
            this.buttonCancel.UseVisualStyleBackColor = true;
            // 
            // textBoxFrom
            // 
            resources.ApplyResources(this.textBoxFrom, "textBoxFrom");
            this.textBoxFrom.Name = "textBoxFrom";
            // 
            // textBoxTo
            // 
            resources.ApplyResources(this.textBoxTo, "textBoxTo");
            this.textBoxTo.Name = "textBoxTo";
            // 
            // radioButtonPages
            // 
            resources.ApplyResources(this.radioButtonPages, "radioButtonPages");
            this.radioButtonPages.Checked = true;
            this.radioButtonPages.Name = "radioButtonPages";
            this.radioButtonPages.TabStop = true;
            this.radioButtonPages.UseVisualStyleBackColor = true;
            this.radioButtonPages.CheckedChanged += new System.EventHandler(this.radioButtonPages_CheckedChanged);
            // 
            // labelFrom
            // 
            resources.ApplyResources(this.labelFrom, "labelFrom");
            this.labelFrom.Name = "labelFrom";
            // 
            // labelTo
            // 
            resources.ApplyResources(this.labelTo, "labelTo");
            this.labelTo.Name = "labelTo";
            // 
            // radioButtonFiles
            // 
            resources.ApplyResources(this.radioButtonFiles, "radioButtonFiles");
            this.radioButtonFiles.Name = "radioButtonFiles";
            this.radioButtonFiles.UseVisualStyleBackColor = true;
            this.radioButtonFiles.CheckedChanged += new System.EventHandler(this.radioButtonFiles_CheckedChanged);
            // 
            // textBoxNumOfPages
            // 
            resources.ApplyResources(this.textBoxNumOfPages, "textBoxNumOfPages");
            this.textBoxNumOfPages.Name = "textBoxNumOfPages";
            // 
            // labelNumOfPages
            // 
            resources.ApplyResources(this.labelNumOfPages, "labelNumOfPages");
            this.labelNumOfPages.Name = "labelNumOfPages";
            // 
            // textBoxInput
            // 
            resources.ApplyResources(this.textBoxInput, "textBoxInput");
            this.textBoxInput.Name = "textBoxInput";
            this.textBoxInput.ReadOnly = true;
            // 
            // textBoxOutput
            // 
            resources.ApplyResources(this.textBoxOutput, "textBoxOutput");
            this.textBoxOutput.Name = "textBoxOutput";
            this.textBoxOutput.ReadOnly = true;
            // 
            // buttonBrowseInput
            // 
            resources.ApplyResources(this.buttonBrowseInput, "buttonBrowseInput");
            this.buttonBrowseInput.Name = "buttonBrowseInput";
            this.buttonBrowseInput.UseVisualStyleBackColor = true;
            this.buttonBrowseInput.Click += new System.EventHandler(this.buttonBrowseInput_Click);
            // 
            // buttonBrowseOutput
            // 
            resources.ApplyResources(this.buttonBrowseOutput, "buttonBrowseOutput");
            this.buttonBrowseOutput.Name = "buttonBrowseOutput";
            this.buttonBrowseOutput.UseVisualStyleBackColor = true;
            this.buttonBrowseOutput.Click += new System.EventHandler(this.buttonBrowseOutput_Click);
            // 
            // labelInput
            // 
            resources.ApplyResources(this.labelInput, "labelInput");
            this.labelInput.Name = "labelInput";
            // 
            // labelOutput
            // 
            resources.ApplyResources(this.labelOutput, "labelOutput");
            this.labelOutput.Name = "labelOutput";
            // 
            // SplitPdfDialog
            // 
            this.AcceptButton = this.buttonSplit;
            resources.ApplyResources(this, "$this");
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.CancelButton = this.buttonCancel;
            this.Controls.Add(this.labelOutput);
            this.Controls.Add(this.labelInput);
            this.Controls.Add(this.buttonBrowseOutput);
            this.Controls.Add(this.buttonBrowseInput);
            this.Controls.Add(this.textBoxOutput);
            this.Controls.Add(this.textBoxInput);
            this.Controls.Add(this.labelNumOfPages);
            this.Controls.Add(this.textBoxNumOfPages);
            this.Controls.Add(this.radioButtonFiles);
            this.Controls.Add(this.labelTo);
            this.Controls.Add(this.labelFrom);
            this.Controls.Add(this.radioButtonPages);
            this.Controls.Add(this.textBoxTo);
            this.Controls.Add(this.textBoxFrom);
            this.Controls.Add(this.buttonCancel);
            this.Controls.Add(this.buttonSplit);
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "SplitPdfDialog";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button buttonSplit;
        private System.Windows.Forms.Button buttonCancel;
        private System.Windows.Forms.TextBox textBoxFrom;
        private System.Windows.Forms.TextBox textBoxTo;
        private System.Windows.Forms.RadioButton radioButtonPages;
        private System.Windows.Forms.Label labelFrom;
        private System.Windows.Forms.Label labelTo;
        private System.Windows.Forms.RadioButton radioButtonFiles;
        private System.Windows.Forms.TextBox textBoxNumOfPages;
        private System.Windows.Forms.Label labelNumOfPages;
        private System.Windows.Forms.TextBox textBoxInput;
        private System.Windows.Forms.TextBox textBoxOutput;
        private System.Windows.Forms.Button buttonBrowseInput;
        private System.Windows.Forms.Button buttonBrowseOutput;
        private System.Windows.Forms.Label labelInput;
        private System.Windows.Forms.Label labelOutput;
    }
}

