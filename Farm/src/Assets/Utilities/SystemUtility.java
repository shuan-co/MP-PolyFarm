package Assets.Utilities;

import java.net.URL;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;

import javafx.scene.Group;
import javafx.scene.shape.MeshView;

// Static Utility class, will contain methods such as importing mesh and etc.
public final class SystemUtility {
    /*
        Import 3DMesh Objects .obj files
        Utilizes an external library for importing 3DMeshes from .obj files, as of the moment javafx does not have
        the capability to do it on its own therefore we used a library from interactivemesh.org

        This function will load the 3D .obj file using the external library which will create a group and
        add each mesh to the group and return it.
    */
    public static Group loadModel(URL url){
        Group modelRoot = new Group();
        ObjModelImporter importer = new ObjModelImporter();
        importer.read(url);

        for (MeshView view : importer.getImport())
            modelRoot.getChildren()
                     .add(view);

        return modelRoot;
    }
}
