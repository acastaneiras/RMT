using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
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
using Path = System.IO.Path;
namespace RMT
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private const String APP_NAME = "Ray-mmd Material Tool v2.0";
        private enum MAP_MODES : int {LINEAR_COLOR =0, SRGB = 1, LINEAR_SRGB = 2 };


        private RayMaterial material;

        public bool initializationEnded;
        //Directory where the materials are created by default... TODO
        private String applicationBaseCreationPath = Directory.GetCurrentDirectory();
        private string imageFilters = "All Graphics Types|*.bmp;*.jpg;*.jpeg;*.png;*.tif;*.tiff"+ "BMP(*.bmp)|*.bmp|GIF(*.gif)|*.gif|JPG(*.jpg)|*.jpg;*.jpeg|PNG(*.png)|*.png|TIFF(*.tif)|*.tif;*.tiff|";
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
            this.albedoMapFile.Content = this.material.AlbedoMapFile;
            this.albedo.Text = this.material.Albedo;
            ChangeMapScaleMode(this.material.Albedo, this.albedoMode);

        }
        /*
         * Used to know in which mode the xMapScale is 
         * Case scenarios: 1.0 | float3(255, 0.0, 0.0) / 255.0 | pow(float3(r, g, b) / 255.0, 2.2)
         */
        private void ChangeMapScaleMode(String mapScale, ComboBox comboBoxMode)
        {
            switch (mapScale)
            {
                case string a when !a.Contains("float"):
                    comboBoxMode.SelectedIndex = (int)MAP_MODES.LINEAR_COLOR;
                    break;
                case string a when a.Contains("pow"):
                    comboBoxMode.SelectedIndex = (int)MAP_MODES.LINEAR_SRGB; 
                    break;
                default:
                    comboBoxMode.SelectedIndex = (int)MAP_MODES.SRGB;
                    break;
            }
        }

        /*
         * Function that gets called everytime that there's a change in the material
         * bool write parameter is used to disable this write feature, mainly when values are initialized
         */
        private void handleChanges()
        {
            if (initializationEnded && !this.material.FilePath.Equals("")) 
                //Call to Save function
                    this.material.Save();
        }

        private void enablePanel()
        {
            if (!this.material.FilePath.Equals("") && TabPanel.IsEnabled == false)
                TabPanel.IsEnabled = true;
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
                    material.FilePath = Path.GetFullPath(saveFileDialog1.FileName);
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
            openFileDialog.AddExtension = true;
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
            String mapFile = handleOpenDialog("c:\\", imageFilters);
            if (!mapFile.Equals(""))
            {
                this.material.AlbedoMapFile = Util.GetRelativePath(this.material.FilePath, mapFile);
                this.albedoMapFile.Content = this.material.AlbedoMapFile;
                handleChanges();
            }
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

        private void albedoMode_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            switch (this.albedoMode.SelectedIndex)
            {
                case (int) MAP_MODES.LINEAR_COLOR:
                    albedoLinearColor.Visibility = Visibility.Visible;
                    albedoRGB.Visibility = Visibility.Hidden;
                    albedo.IsEnabled = true;
                    break;
                default:
                    albedoLinearColor.Visibility = Visibility.Hidden;
                    albedoRGB.Visibility = Visibility.Visible;
                    albedo.IsEnabled = false;
                    break;
            }
        }

        private void albedoLinearColor_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
        {

        }

        private void albedo_PreviewTextInput(object sender, TextCompositionEventArgs e)
        {
            bool approvedDecimalPoint = false;

            if (e.Text == ".")
            {
                if (!((TextBox)sender).Text.Contains("."))
                    approvedDecimalPoint = true;
            }

            if (!(char.IsDigit(e.Text, e.Text.Length - 1) || approvedDecimalPoint))
                e.Handled = true;
        }

        private void albedo_TextChanged(object sender, TextChangedEventArgs e)
        {
            this.material.Albedo = albedo.Text;
            handleChanges();
        }

        private void albedoRGB_SelectedColorChanged(object sender, RoutedPropertyChangedEventArgs<Color?> e)
        {
            Console.WriteLine(albedoRGB.SelectedColor.ToString());
        }
    }
}
