rootProject.name = "RankedProject"
include("Common")
include("Spigot")
include("Spigot:GameAPI")
findProject(":Spigot:GameAPI")?.name = "GameAPI"
include("Spigot:RankedGame")
findProject(":Spigot:RankedGame")?.name = "RankedGame"
include("Spigot:RankedLobby")
findProject(":Spigot:RankedLobby")?.name = "RankedLobby"
