import SwiftUI
import shared

@main
struct iOSApp: App {

    init() {
        KoinHelperKt.doInitKoinApp()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}