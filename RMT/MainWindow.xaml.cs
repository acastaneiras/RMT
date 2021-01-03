using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using Microsoft.Win32;
using RMT.Model;

namespace RMT
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private const String APP_NAME = "Red's Material Tool v2.0";

        private RayMaterial material;

        public bool initializationEnded;
        //Directory where the materials are created by default... TODO
        private String applicationBaseCreationPath = Directory.GetCurrentDirectory();

        public MainWindow()
        {
            initializationEnded = false;
            material = new RayMaterial();
            InitializeComponent();
            InitializeValues();
            initializationEnded = true;
        }

        private void InitializeValues()
        {
            this.albedoMapFrom.SelectedIndex =this.material.AlbedoMapFrom;
            this.albedoMapUVFlip.SelectedIndex = this.material.AlbedoMapUVFlip;
            this.albedoMapApplyScale.SelectedIndex = this.material.AlbedoMapApplyScale;
            this.albedoMapApplyDiffuse.SelectedIndex = this.material.AlbedoMapApplyDiffuse;
            this.albedoMapApplyMorphColor.SelectedIndex = this.material.AlbedoMapApplyMorphColor;
            
        }

        /*
         * Function that gets called everytime that there's a change in the material
         * bool write parameter is used to disable this write feature, mainly when values are initialized
         */
        private void handleChanges()
        {
            if (initializationEnded) 
                //Call to Save function
                if (!this.material.FilePath.Equals(""))
                {
                    this.material.Save();
                } else
                {
                    createNewMaterial();
                }
        }

        private void enablePanel()
        {
            if (!this.material.FilePath.Equals("") && TabPanel.IsEnabled == false)
            {
                TabPanel.IsEnabled = true;
            }
        }

        private void createNewMaterial()
        {
            material = new RayMaterial();

            Stream myStream;
            SaveFileDialog saveFileDialog1 = new SaveFileDialog();

            //saveFileDialog1.InitialDirectory = this.applicationBaseCreationPath;
            saveFileDialog1.Filter = "Material files (*.fx)|*.fx";
            saveFileDialog1.FileName = "New_Material";
			saveFileDialog1.AddExtension = true;
            saveFileDialog1.Title = "Save new material file";
            saveFileDialog1.RestoreDirectory = true;
            bool? result = saveFileDialog1.ShowDialog();

            if (result == true)
            {
                if ((myStream = saveFileDialog1.OpenFile()) != null)
                {
                    material.FilePath = System.IO.Path.GetFullPath(saveFileDialog1.FileName);
                    myStream.Close();
                    material.Save();
                    InitializeValues();
                    enablePanel();
                    this.Title = APP_NAME + " | Editing " + this.GetFileName(material.FilePath);
                }
            }
        }

        private String handleOpenDialog(String initialDirectory = "c:\\", String filter = "Material files (*.fx)|*.fx", String title = "Select map file")
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();

            openFileDialog.InitialDirectory = initialDirectory;
            openFileDialog.Filter = filter;
            openFileDialog.Title = title;
            openFileDialog.RestoreDirectory = true;

            bool? result = openFileDialog.ShowDialog();
            if (result == true)
            {
                //Get the path of specified file
                return openFileDialog.FileName;
            }
            return "";
        }

        private String GetFileName(String filePath)
        {
            return filePath.Substring(filePath.LastIndexOf("\\")+1);
        }

        //UI Events

        private void albedoMapFrom_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            this.material.AlbedoMapFrom = albedoMapFrom.SelectedIndex;
            handleChanges();
        }

        private void albedoMapUVFlip_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            this.material.AlbedoMapUVFlip = albedoMapUVFlip.SelectedIndex;
            handleChanges();
        }

        private void albedoMapApplyScale_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            this.material.AlbedoMapApplyScale = albedoMapApplyScale.SelectedIndex;
            handleChanges();
        }

        private void albedoMapApplyDiffuse_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            this.material.AlbedoMapApplyDiffuse = albedoMapApplyDiffuse.SelectedIndex;
            handleChanges();
        }

        private void albedoMapApplyMorphColor_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            this.material.AlbedoMapApplyMorphColor = albedoMapApplyMorphColor.SelectedIndex;
            handleChanges();
        }

        private void albedoMapFile_Click(object sender, RoutedEventArgs e)
        {


        }

        private void newMaterial_Click(object sender, RoutedEventArgs e)
        {
            createNewMaterial();
        }

        private void exit_Click(object sender, RoutedEventArgs e)
        {
            Application.Current.Shutdown();
        }

        private void editMaterial_Click(object sender, RoutedEventArgs e)
        {
            material = new RayMaterial();
            String fileName = this.handleOpenDialog(Directory.GetCurrentDirectory(), "Material files (*.fx)|*.fx", "Open material file");
            if (!fileName.Equals(""))
            {
                material.FilePath = System.IO.Path.GetFullPath(fileName);
                material.FetchMaterial();
                InitializeValues();
                enablePanel();
                this.Title = APP_NAME + " | Editing " + this.GetFileName(fileName);
            }

        }
    }
}
