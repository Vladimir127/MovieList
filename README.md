# Movies

Приложение для просмотра списка фильмов в виде ленты. Разработано на языке Kotlin в среде Android Studio. Для загрузки данных используется Retrofit. Реализована Dependency Injection с помощью библиотеки Dagger. 

Приложение состоит из двух активити: 

## SplashActivity

Сплеш экран с названием приложения и иконкой приложения по центру. 

<img src="https://user-images.githubusercontent.com/76528795/181908022-b7f43afb-719f-4ac9-9cc8-d8282d710541.png" width="32%">

## ListActivity

Содержит RecyclerView со списком фильмов. Для каждого фильма отображаются название, описание, картинка. Для отображения списка используется элемент RecyclerView, для загрузки изображений - библиотека Glide. 

<img src="https://user-images.githubusercontent.com/76528795/181908054-2f9270c5-a39e-4fcd-9089-05a47803da3e.png" width="32%">

При прокрутке списка реализована пагинация: записи загружаются порциями по 20 штук. Во время загрузки в нижней части списка отображается крутящийся ProgressBar. 

<img src="https://user-images.githubusercontent.com/76528795/181908075-f4680470-8c14-4744-bbd7-0adad606fabc.png" width="32%">
