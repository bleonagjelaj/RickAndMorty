import SwiftUI
import shared

@main
struct iOSApp: App {

    init(){
        KoinHelper.initKoinApp()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}