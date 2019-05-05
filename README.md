# GitHubApiClient

A simple project for presenting github's repos based on **MVVM clean architecture**.

#### App Features
* finding repos that belongs to user.
* displaying repo details.

#### App architecture

<img src="https://github.com/Leedwon/GitHubApiClient/blob/master/media/tmp.png" width="500" style="max-width:500%;">

* github api calls with retrofit
* repository working with api provides RxJava2 Observables
* viewModels observe repository calls and provide data specific for ui
* ui showing visual representation of data in viewmodel
* simple unit tests

#### App packages
* <b>data</b> - contains 
    * <b>model</b> - contains classes responsible for implementing repo and repoOwner.
    * <b>GitHubApi.java</b> - api class responsible for network calls with usage of Retrofit.
    * <b>GitHubRepository.java</b> - repository class responsible for triggering api requests and providing Observables.
* <b>di</b> - contains dependency injection classes, using Dagger2.   
* <b>ui</b> - contains classes needed to display Activities and Fragments.
   * <b>viewmodels</b> contains viewmodels each of them provides LiveData specific for given ui.
* <b>utils</b> - contains classes that helps with networking, databinding, json conversions.
