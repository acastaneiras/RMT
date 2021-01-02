using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WpfApp1.Model
{
    class Test
    {
        public static void Main(string[] args)
        {
            RayMaterial m = new RayMaterial();
            m.FetchMaterial("D:/MikuMikuDanceE_v932x64/UserFile/Accessory/ray-mmd-1.5.2/Materials/material_2.0.fx");
            Console.WriteLine("sss"+ m.AlbedoMapFile+"sd");
        }
    }
}
