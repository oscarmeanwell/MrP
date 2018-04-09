#Bash script to switch onto final branch and update it with master
#The switches you back onto master
echo -n "Have you pushed changes to origin/master? y/n: "
read -n 1 answer
if [ "$answer" = "y" ]; then
	printf "\nLets do it\n"
	git checkout final
	git pull origin master
	git push
	git checkout master
	printf "\n Done, now go have a brew!\n"
else
	printf "\n\n"
	echo -n "Want me to do it for you? y/n: "
	read -n 1 ans
	if [ "$ans" = "y" ]; then
		git add .
		git commit -m "Automated commit"
		git push
		git checkout final
		git pull origin master
		git push
		git checkout master
        printf "\n Done, now go have a brew!\n"
	else
		printf "\nDont trust me do you?\n"
	fi
fi
