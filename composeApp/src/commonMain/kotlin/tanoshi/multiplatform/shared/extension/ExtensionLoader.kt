package tanoshi.multiplatform.shared.extension

import androidx.compose.runtime.Composable
import tanoshi.multiplatform.common.extension.core.Extension
import java.io.File

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class ExtensionLoader {

    var startDynamicActivity : ((@Composable ()->Unit).() -> Unit)?

    // pair( pair(package name , archive name) , extension )
    val loadedExtensionClasses : ArrayList< Pair< Pair<String,String> , Extension> >
    
    fun loadTanoshiExtension(jarOrDexFile : File, classNameList : List<String> )

}