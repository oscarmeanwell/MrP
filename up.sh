echo -n "Have you pushed changes to origin/master? y/n: "
read -n 1 answer
if [ "$answer" = "y" ]; then
	printf "\nLets do it\n"
	git checkout final
	git pull origin master
	git push
	git checkout master
else
	echo "bye"
fi
