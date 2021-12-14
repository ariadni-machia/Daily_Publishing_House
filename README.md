# Daily_Publishing_House
 The Database concerns an electronic support system for the operation of a Daily Press Publishing House. Journalists who write articles, editors, administrative and the publisher of each newspaper are connected to this system. 
# Part A: Database and SQL
In the first phase we had to carefully study the initial design of the NW that gives us the requirements of the project in both Part A and B. Based on these functional requirements you will have to review the original design. For example, the BD should be expanded to maintain information about the process of checking an article submitted to the system by a journalist.The checking is done by the editor and depending on his judgment he will be able to: approve it, reject it or ask for changes and re-submission for control. The necessary changes should also be made to support the process of determining the contents of a sheet (ie selection of articles that will include their classification in order), a process that will be done by the editor. We had to build the following:
1. Stored procedure that will accept as input the number of the sheet and the name of the newspaper and will display in a properly formatted the elements of the articles of the sheet in the order in which they have been placed. Each article will display Title, Author (s), Approval Date, start page and total length (number of pages). In case the sheet is not 'closed' (ie it has empty space for additional articles) a relevant message should be displayed with the available pages.
2. Stored procedure that will recalculate an employee's salary based on months of service. More specifically, he will be called for a specific journalist and will check the date of his registration. It will calculate the total months of previous service by adding to the previous service declared during the registration in the system the months that have passed since then. Based on the months of total service, he will calculate a basic salary increase of 0.5% for each month of service.
3. Trigger that will be activated when a new employee is hired and will set his salary at 650 euros.
4. Trigger that when inserting a new entry in the table that connects a journalist with an article will check if the journalist is the editor and if so, will "accept" the article.
5. Trigger that when articles are selected and inserted in a sheet will check if the sheet has the necessary space in pages. Otherwise the add-on will fail and an error message will be displayed. 

# Part B: GUIs
We were asked to build the 3 interfaces described below in Java using IDEs of your choice. *We chose Eclipse and to implement the users: Journalist, Administrative and Publisher*.<br/>
In addition, we had to create a login home page where each user would enter a username and password. Depending on the category of each user, the appropriate one from the following GUIs will be displayed: <br/>

The __Journalist__ will be able to:
- Submit a new article to the working newspaper. It should enter all the elements of the article (file storage path with the content of the article, summary, thematic category/ies to which it belongs, any additional authors, keywords, page length, accompanying photos or images).
- See the details of the articles he has written (either alone or in collaboration with another journalist), as well as the status of the articles regarding the checking of the editor:
  - accepted and in this case the no. of the sheet in which they were published (if any)
  - to_be_revised
  - rejected
- Submit a revised version of an article. <br/>

The __Administrative__ will be able to:
- Enter in the system the number of sheets which were returned (not sold).
- To see aggregate financial data of payroll by entering a period in whole months (ie to see the total amount of money spent on salaries either per employee or in total). <br/>

The __Publisher__ will be able to: 
- Process the data of the newspaper/s that belongs to him/her.
- Specify the number of copies that should be printed for each sheet.
- Appoint the editor of the newspaper/s.
- See aggregate sheet sales data (copies issued minus sheets returned per sheet). <br/>

Where possible in the GUI we *restricted* the input of data by the user to *avoid errors and speed up procedures* (e.g. to select from menus / lists). The options that will be available will come from the data available in the DB (e.g. if an editor wants to choose the articles that will be on a sheet he will be able to choose from the articles submitted by journalists of the specific newspaper, the which he has approved and have not been published in a previous sheet), or if an edit is to be made, the data already available from the DB will be displayed and if there are items that cannot be changed they will be displayed as uneditable ('locked') ).

# Team Members
- Ariadni Machia
- Aspasia Koukouvela <br/>

> __Date of project:__ 2019-2020

# User's Information
In order to see the features of gui, it is recommended to use the following: <br/>
User Type | Username | Password
| :--- | ---: | :---:
Journalist  | blair | b12
Publisher  | marie | 123
Administrative  | polly | 1234
