package tabs.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

data class HomeScreen(
    val onClick:()->Unit,
) : Screen {

    @Composable
    override fun Content() {
        val navigator: Navigator = LocalNavigator.currentOrThrow
        val size=navigator.size
        println("home size:$size")

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Button(onClick = onClick){
                Text("hide/show tab navigation")
            }
            Text(text = "Home")

            Button(onClick = {
                navigator.push(DetailsScreen(id = 1,onClick=onClick))
                //to change the visibility of the tab navigation
                onClick()
            }) {
                Text(text = "Go to example details")
            }
        }
    }
}
