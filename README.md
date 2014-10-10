To compile this project :

I just explain the way to compile this project by using github for windows.
So download github for windows : https://windows.github.com/

Login in and make it ready to clone a github repository.
Now you can clone the repository to get sources : https://github.com/4x10m/Corpo.git

Download NodeClipse : http://www.nodeclipse.org/enide/studio/
Download ADT Bundle : https://developer.android.com/sdk/index.html?hl=i
Download MongoDB : http://www.mongodb.org/

Make a database "node-android" (maybe with robomongo).

Open each IDEs and import projects (select generic project), serveur on nodeclipse and client in adt.

Right click package.json in nodeclipse, run as, package.json.
Right click app.js, run as, node app.

In adt bundle, open project and click run.

The app will launch but not work (expect a network error).

I can give you 2 means to make the system working.
If you have a local network, set your phone and your pc on wifi and type ip address of each other in both configuration.
Also you can make an internet tethering between your pc and your phone and type ip address of each in both configuration 
(this work with usb tethering).