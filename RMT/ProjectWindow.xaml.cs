using RMT.Model;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Diagnostics;
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
using System.Windows.Shapes;

namespace RMT
{
    /// <summary>
    /// Interaction logic for ProjectWindow.xaml
    /// </summary>
    /// 
    public class ProjectItem
    {
        public ProjectItem()
        {
            this.Items = new ObservableCollection<ProjectItem>();
        }

        public string Title { get; set; }

        public ObservableCollection<ProjectItem> Items { get; set; }
    }

    public partial class ProjectWindow : Window
    {

        private EMM currentProject;

        public ProjectWindow(EMM project)
        {
            currentProject = project;
            InitializeComponent();

            FillTreeView();
        }

        private void FillTreeView()
        {
            PMD[] pmds = currentProject.Pmds;
            ProjectItem root = new ProjectItem() { Title = "Root" + " | " + currentProject.FilePath };

            for (int i = 0; i < pmds.Length; i++)
            {
                ProjectItem pmdX = new ProjectItem() { Title = pmds[i].Code+ " | " + pmds[i].MainMaterial.FilePath };

                Subset[] subs = pmds[i].Subsets;
                for (int j = 0; j < subs.Length; j++)
                {
                    ProjectItem pmdXItem = new ProjectItem() { Title = subs[j].IsShown + " | " + subs[j].Num + " | " + subs[j].Material.FilePath };

                    pmdX.Items.Add(pmdXItem);
                }
                root.Items.Add(pmdX);
            }
            projectView.Items.Add(root);
        }


        private void editButton_Click(object sender, RoutedEventArgs e)
        {

        }
    }
}
