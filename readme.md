# Detection Script Usage

- I adapt the detection tool from https://github.com/platisd/duplicate-code-detection-tool

## Dependencies

The following Python packages have to be installed:

- nltk
  - `pip3 install --user nltk`
- gensim
  - `pip3 install --user gensim`
- astor
  - `pip3 install --user astor`
- punkt
  - `python -m nltk.downloader punkt`

## Usage

- Unzip the Reference.zip (which contains 20 repositories from github. You can add more).
- Put the duplicate_detect_java.py script, the Reference folder, and all your group assignment together as the following screenshot:

![tool_usage](E:\Courses\CZ2002TA\Duplicate\Summary\markdown\tool_usage.PNG)

- Open your IDE, modify the script to match your group names:![tool_usage2](E:\Courses\CZ2002TA\Duplicate\Summary\markdown\tool_usage2.PNG)

- Run it, you will see something like this:
- ![tool_usage3](E:\Courses\CZ2002TA\Duplicate\Summary\markdown\tool_usage3.PNG)

## Important note

- The similarity analysis is based on the topic model TFIDF, so **there will be many false positives.**

- **The reported results cannot substitute human analysis.**
- Normally speaking, the duplicated code will be similar to only one file. One duplicated project will contain 20+ duplicated files.
- You can change the threshold to adjust the sensitivity (I set it to 60 in my case).
