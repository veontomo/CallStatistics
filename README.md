# CallStatistics
GDG Rome final project.

The application shows the histogram of your recent calls. In other words, it displays how many times a specific phone number is present in your phone call log.

This toy application takes into consideration only how frequent a phone number appears in the call log. As a future direction, it might be interesting as well to consider the call directions, their durations and so on. 

![Application screenshot](https://github.com/veontomo/CallStatistics/blob/master/CallStatisticsScreenshot.png)

The data is drawn as a standard histogram and is displayed as well as a simple list.

The app specs:

1. it contains a single activity
2. it contains two text views, one spinner, one custom list and one custom view
3. it is built using MVP approach
4. it uses RxJava to fetch data from the call log.


In future it would be interesting to use RxJava in order to add some animation when plotting the histogram.

Apk files are located here: 
1. [apk-debug](../v1/apk/app-debug.apk)
2. [app-debug-unaligned](../v1/apk/app-debug-unaligned.apk)


