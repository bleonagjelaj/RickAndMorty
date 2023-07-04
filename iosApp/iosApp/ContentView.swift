import SwiftUI
import shared
import Foundation

struct ContentView: View {
	var body: some View {
	    CharactersScreen()
	}
}



struct CharactersScreen: View {
    @ObservedObject var viewModel: IOSCharactersViewModel

    init() {
        self.viewModel = IOSCharactersViewModel()
    }

    var body: some View {
        Text("Rick and Morty")
        if #available(iOS 16, *) {
            NavigationStack{
                List(viewModel.state.characters, id: \.self) { characterModel in
                    NavigationLink{
                        CharacterDetailsScreen(characterId: characterModel.characterId)
                    } label:  {
                        CharacterRow(character: characterModel)
                    }
                }
            }
        }
    }
}

struct CharacterRow: View {
    let character: CharacterModel
    var body: some View {
        HStack {
            Text(String(character.characterId))
                .padding(EdgeInsets(top: 0, leading: 0, bottom: 0, trailing: 20))
            if #available(iOS 15, *) {
                AsyncImage(url: URL(string: character.pictureUrl)){ image in
                    image.resizable()
                } placeholder: {
                    ProgressView()
                }
                .frame(width: 60, height: 60)
            }
            Text(character.name).padding(5)
        }
    }
}

extension CharactersScreen {
    @MainActor class IOSCharactersViewModel: ObservableObject {
        private let viewModel : CharactersViewModel
        private var handle: DisposableHandle?
        @Published var state: CharactersState = CharactersState(characters: [])

        init() {
            self.viewModel = CharactersViewModel(coroutineScope: nil)
            viewModel.getStateUpdate()
            getCharacters()
        }

        func getCharacters() {
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                }
            })
        }

        func dispose() {
            handle?.dispose()
        }
    }
}



struct CharacterDetailsScreen: View {
    let characterId: Int32
    @ObservedObject var viewModel: IOSCharacterDetailsViewModel

    init(characterId: Int32) {
        self.characterId = characterId
        self.viewModel = IOSCharacterDetailsViewModel(characterId: characterId)
    }

    var body: some View {
        if #available(iOS 15, *) {
            AsyncImage(url: URL(string: viewModel.state.characterDetails.pictureUrl)) { image in
                image.resizable()
            } placeholder: {
                ProgressView()
            }
            .frame(width: 250, height: 250)
        }
        if #available(iOS 16, *) {
            Text(viewModel.state.characterDetails.name)
                .padding(EdgeInsets(top: 10, leading: 0, bottom: 10, trailing: 0))
                .fontWeight(.bold)
                .font(.system(size: 25))
        }
        HStack {
            Text("Status: ")
            Text(viewModel.state.characterDetails.status)
        }.padding(EdgeInsets(top: 0, leading: 0, bottom: 1, trailing: 0))
        HStack {
            Text("Gender: ")
            Text( viewModel.state.characterDetails.gender)
        }.padding(EdgeInsets(top: 0, leading: 0, bottom: 1, trailing: 0))
        HStack {
            Text("Species: ")
            Text(viewModel.state.characterDetails.species)
        }.padding(EdgeInsets(top: 0, leading: 0, bottom: 1, trailing: 0))
        Text("Origin: ") + Text(viewModel.state.characterDetails.origin)
    }
}

extension CharacterDetailsScreen {
    @MainActor class IOSCharacterDetailsViewModel: ObservableObject {
        let characterId: Int32
        private let viewModel : CharacterDetailsViewModel
        private var handle: DisposableHandle?
        @Published var state: CharacterDetailsState =
            CharacterDetailsState(characterDetails: CharacterDetailsModel(
                name : "",
                status : "",
                species : "",
                gender : "",
                origin : "",
                pictureUrl : ""
            ))

        init(characterId: Int32) {
            self.characterId = characterId
            self.viewModel = CharacterDetailsViewModel(coroutineScope: nil)
            viewModel.getStateUpdate(characterId: characterId)
            getCharacterDetails()
        }

        func getCharacterDetails() {
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                }
            })
        }

        func dispose() {
            handle?.dispose()
        }
    }
}