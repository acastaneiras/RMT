using System;
using System.Diagnostics;
using System.Windows;

namespace RMT.View
{
    /// <summary>
    /// Interaction logic for Help.xaml
    /// </summary>
    public partial class Help : Window
    {
        private static bool willNavigate;

        public Help()
        {
            InitializeComponent();
        }

        public Help(string section)
        {
            InitializeComponent();
            var curDir = AppDomain.CurrentDomain.BaseDirectory;
            var folder = System.IO.Directory.GetParent(curDir).Parent.Parent;
            HTMLDisplayer.Source = new Uri(folder.FullName + "\\View\\Pages\\" + section + ".html");
        }

        private void HTMLDisplayer_Navigating(object sender, System.Windows.Navigation.NavigatingCancelEventArgs e)
        {
            if (!willNavigate)
            {
                willNavigate = true;
                return;
            }

            //Opening external links in browser, local links inside the web browser
            //Local links only contain -> file:// 
            if (!e.Uri.ToString().Contains("file://"))
            {
                try
                {
                    ProcessStartInfo startInfo = new ProcessStartInfo
                    {
                        FileName = e.Uri.ToString()
                    };

                    Process.Start(startInfo);
                }
                catch (Exception)
                {

                }
            }
        }
    }
}
