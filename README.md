# GitHub Api Client

A simple project for presenting github's repos based on **MVVM clean architecrure**.

#### App Features
* finding repos that belongs user.
* displaying repo details.

<img src="https://github.com/Leedwon/GitHubApiClient/master/media/tmp.png" width="500" style="max-width:500%;">

#### App architecture
* github api calls with retrofit
* repository working with api providing LiveData that is may be observed
* viewModels provides data specific for ui
* ui showing visual representation of data in viewmodel
* simple unit tests

#### App packages
* <b>data</b> - contains 
    * <b>model</b> - contains classes responsible for implementation of repo and repoOwner.
    * <b>responses</b> - wrapper around model that helps with error handling
    * <b>GitHubApi.java</b> - api class responsible for network calls with usage of Retrofit
    * <b>GitHubRepository.java</b> - repository class responsible for triggering api requests and providing LiveData.
* <b>di</b> - contains dependency injection classes, using Dagger2.   
* <b>ui</b> - contains classes needed to display Activity and Fragment.
* <b>utils</b> - contains classes that helps with networking, databinding, json conversions.
