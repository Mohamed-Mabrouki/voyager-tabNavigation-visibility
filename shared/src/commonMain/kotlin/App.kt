import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import tabs.home.HomeTab
import tabs.settings.SettingsTab

@Composable
fun App() {
	MaterialTheme {
		var isVisible by remember { mutableStateOf(true) }
		TabNavigator(
			tab = HomeTab(onClick = {
				isVisible = !isVisible
				println("tab visibility:$isVisible")
			})
		) {
			val tabNavigator = LocalTabNavigator.current

			Scaffold(
				modifier = Modifier.fillMaxSize(),
				bottomBar = {
					AnimatedVisibility(visible = isVisible, enter = slideInVertically { height ->
						height
					}, exit = slideOutVertically { height ->
						height
					}) {
						BottomNavigation {
							TabNavigationItem(HomeTab(onClick = {
								isVisible = !isVisible
							}))
							TabNavigationItem(SettingsTab)
						}
					}
				},
				content = { CurrentTab() },
			)
		}
	}
}
@Composable
private fun RowScope.TabNavigationItem(tab : Tab) {
	val tabNavigator : TabNavigator = LocalTabNavigator.current

	BottomNavigationItem(selected = tabNavigator.current == tab,
		onClick = { tabNavigator.current = tab },
		icon = {
			tab.options.icon?.let { icon ->
				Icon(
					painter = icon, contentDescription = tab.options.title
				)
			}
		},
		label = {
			Text(
				text = tab.options.title
			)
		})
}

expect fun getPlatformName() : String