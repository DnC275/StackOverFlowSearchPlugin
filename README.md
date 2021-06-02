# StackOverFlowSearchPlugin
Plugin for searching for questions on stackoverflow by selected text.

## Usage

Select a section of text and press `Ctrl+\, Ctrl+S`(exactly in that order!). After 1-2 seconds, a popup window will appear, in which the search result(a list of questions) on stackoverflow for the selected text, sorted in descending order of votes. Select one question and then the browser will open a tab with this question on stackoverflow.</p>
**Note**: The selected text must not contain tabs or newlines characters, but may contain spaces.</p>
**Note**: Make sure you have internet connection

## Sample of usage

<img src="https://media.giphy.com/media/UJnfCsEBzeJmSLMtGd/giphy.gif" width="50%"></p>

## Building from the source

1. Clone the repository using the following command.

    ```
    git clone https://github.com/DnC275/StackOverFlowSearchPlugin.git
    ```
2. ### Linux/macOs
   Navigate into the cloned repository and run `./gradlew`. In the **build/distributions** directory, **.zip** file with plugin will be created.
   
   ###Windows
   Navigate into the cloned repository and run `gradlew`. In the **build/distributions** directory, **.zip** file with plugin will be created.

## Installing the plugin to Intellij Idea

1. Go to **File -> Settings** and select **Plugins**.
2. Click **Install plugin from disc** and select the deployed **plugin zip** file.
3. Restart IDEA.