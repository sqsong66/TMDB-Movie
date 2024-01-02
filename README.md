<p align="center">
    <img width="256px" src="images/logo_circle.svg" />
</p>

</br>
</br>

<p align="center">
  <strong>
    An android movie APP base on 
    <a href="https://developer.themoviedb.org/docs">TMDB API</a>
  </strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/build-passing-brightgreen?logo=github" />
  <a href="LICENSE">
    <img src="https://img.shields.io/badge/License-Apache_2.0-blue" />
  </a>
  <img src="https://img.shields.io/badge/release-v1.0.0-green" />
</p>

<p align="center">
    The <a href="https://github.com/sqsong66/TMDB-Movie">TMDB Movie</a> is my <a href="https://developer.android.com/jetpack/compose">Jetpack Compose</a> learning project, and it's all code written by Kotlin Jetpack Compose.
    Thanks for <a href="https://www.themoviedb.org/">TMDB</a>(The Movie Database) provide the API, this project backend interface is based on <a href="https://developer.themoviedb.org/docs">TMDB API</a>. 
</p>

<div align="center">
<table>
  <tr>
    <td> 
      <p align="center">
      <img alt="Home Page" width="300" src="images/Screenshot_20231017_171922.png" /> <br>
      <em>Home Page(Light Mode)</em>
      </p>
    </td>
    <td> 
      <p align="center">
      <img alt="Home Page" width="300" src="images/Screenshot_20231017_170327.png" /> <br>
      <em>Home Page(Dark Mode)</em>
      </p>
    </td>
  </tr>
</table>
</div>

Chinese ducument see [中文文档](README_CN.md).

The APP ui design specifications follow [Material Design3](https://m3.material.io/), it's beautiful and support Dark, Light and Dynamic theme(Android 12+).

</br>

> [!WARNING]
> This app is just my learning demo project, not the TMDB offical project. It can only be used for learning and communication, please do not use it for other purposes.

</br>

## Build and Run  
1. Go to [TMDB offical website](https://www.themoviedb.org/settings/api) register an account and apply to obtain Access Token.
2. In the project root directory, create `local.properties` file(if the file does not exist), put the access in it.
   ```
   TMDB_ACCESS_TOKEN = YOU_TMDB_ACCESS_TOKEN
   ```
3. In this project I generate a release keystore to build released apk, you maybe dont't need it, you can delete app's `build.gradle.kts` `signingConfigs` configuration.
4. Sync and run to build apk.

## Supported Features
- [x] Popular movies, TVs People.
- [x] Trending movies, TVs.
- [x] Now playing movies, TVs.
- [x] Discovery movies, TVs.
- [x] Authentication login.
- [x] Movie, TV, people details.
- [x] Add movie, TV to favorite.
- [x] Add movie, TV to watchlist.
- [x] Create list.
- [x] Add movie, TV to list.
- [x] Share movive, TV.
- [x] Change theme.
- [x] Search movie, TV, people.
- [x] Rate movie, TV.
- [x] My list and list detials.
- [x] My watchlist movies, TVs.
- [x] My favorite movies, TVs.
- [x] My rated movies, TVs.
- [ ] ...

## ScreenShots
<div align="left">
<table>
  <tr>
    <td> 
      <p align="center">
      <img alt="Discovery Movies" width="300" src="images/1.png" /> <br>
      <em>Discovery Movies(Light Mode)</em>
      </p>
    </td>
    <td> 
      <p align="center">
      <img alt="Discovery TVs" width="300" src="images/2.png" /> <br>
      <em>Discovery TVs(Light Mode)</em>
      </p>
    </td>
  </tr>

  <tr>
    <td> 
      <p align="center">
      <img alt="Theme Setting" width="300" src="images/3.png" /> <br>
      <em>Theme Setting(Light Mode)</em>
      </p>
    </td>
    <td> 
      <p align="center">
      <img alt="Theme Setting" width="300" src="images/4.png" /> <br>
      <em>Theme Setting(Dark Mode)</em>
      </p>
    </td>
  </tr>

  <tr>
    <td> 
      <p align="center">
      <img alt="Movie Details" width="300" src="images/5.png" /> <br>
      <em>Movie Details(Light Mode)</em>
      </p>
    </td>
    <td> 
      <p align="center">
      <img alt="Movie Details" width="300" src="images/6.png" /> <br>
      <em>Movie Details(Light Mode)</em>
      </p>
    </td>
  </tr>

  <tr>
    <td> 
      <p align="center">
      <img alt="People Details" width="300" src="images/7.png" /> <br>
      <em>People Details(Dark Mode)</em>
      </p>
    </td>
    <td> 
      <p align="center">
      <img alt="Actor's Acting" width="300" src="images/8.png" /> <br>
      <em>Actor's Acting(Dark Mode)</em>
      </p>
    </td>
  </tr>
</table>
</div>

## Screen Video
https://github.com/sqsong66/TMDB-Movie/assets/11425148/5e9cb590-6042-4444-a47f-9bfe6df9d67c

## Thanks 
[TMDB(The Movie Database)](https://www.themoviedb.org/)  
[nowinandroid](https://github.com/android/nowinandroid)  
[Jetpack Compose](https://developer.android.com/jetpack/compose)

## LICENSE
See [LICENSE](LICENSE)