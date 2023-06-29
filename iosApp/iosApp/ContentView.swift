import SwiftUI
import shared
import Foundation
import _Concurrency

struct ContentView: View {
	@ObservedObject var viewModel: CharactersViewModel = CharactersViewModel()

	var body: some View {
	    NavigationView {
	        List{
	            ForEach(viewModel.characterItems) { characterModel in
	                VStack{
                        CharacterRow(character: characterModel)
	                }
	            }
	        }
	    }
	}
}

struct CharacterRow: View {
var character: CharacterModel
    var body: some View {
           Text(character.name)
    }
}

extension CharacterModel: Identifiable {}

@MainActor
class CharactersViewModel: ObservableObject {
    @Published var characterItems = [CharacterModel]()
    init() {
        do {
            let stream = asyncStream(for: GetCharactersHelper().getCharacters())
            for try await data in stream {
                self.characterItems = data
            }
        } catch {
            print("Failed with error: \(error)")
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}