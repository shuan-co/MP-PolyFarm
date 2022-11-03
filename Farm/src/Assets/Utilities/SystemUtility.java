package Assets.Utilities;

import java.net.URL;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;

import javafx.scene.Group;
import javafx.scene.shape.MeshView;

public final class SystemUtility {
    // Import 3DMesh Objects .obj files
    public static Group loadModel(URL url){
        Group modelRoot = new Group();
        ObjModelImporter importer = new ObjModelImporter();
        importer.read(url);
        for (MeshView view : importer.getImport())
            modelRoot.getChildren().add(view);
        return modelRoot;
    }
}
