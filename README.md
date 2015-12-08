News app is created for searching news, photos and posts nearby user.

### How do I get set up? ###

For setup work with this app you need AndroidStudio on your PC and git integration to your commandLine.

You can download this repository or clone it(using git clone "url" command) and then open it in AndroidStudio and you are ready to work.

NOTE! When you are making commit or changing versionCode make sure you added it will be seen in git description
You can add this using gradle task called setVersion it can be run by 3 different ways:
1) From AndroidStudio in Gradle tasks toolbar select setVersion task
2) From console, if you have installed gradle plugin, in root of News project run gradle setVersion
3) From console, if you haven't gradle plugin, in root of News project run ./gradlew setVersion.
## How do I run the tests? ##

For running integrational tests you need to run AndroidStudio. To work on unit tests, switch the Test Artifact in the Build Variants view. And run the tests rightclicking on such classes: FlickrLoaderTests, UtilsMockTest, ExampleUnitTest, TwitterLoaderTest.