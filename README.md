# MISC PROJECT
FoodFinder - Recipe Application

 - 2 method with the return type an integer value or real value;
	InstructionsAdapter => int getItemCount()
	RandomRecipeAdapter => int getItemCount()

 - 1 method that read something from a file and returns a value based on data from the file;
	FileRW => T read(filename)

 - 1 method that checks if a string is in correct format;
	StringChecker => static boolean check(string, regex)

 - 1 method that works with values from a range;
	HomeActivityViewModel => getRecipesAsListRange(idxMin, idxMax)

 - 1 method that simplifies the process of other method or other two methods by using another algorithm;
	RecipeFinder => binarySearch vs normalSearch 

 - 1 method that is using a lot of time to process something;
	requestHandler => getRandomRecipesSynchronously(nr_of_recipes); 

 - 1 method that obtains a list or an array;
	HomeActivityViewMode => getRecipesAsList() or requestHandler => getRandomRecipesSynchronously(nr_of_recipes);

 - 1 method that is using objects from other classes;
	FileRW => read() or write (uses objects and methods from other Classes)
	Or something from HomeActivityViewmodel or RecipeActivityViewModel
	There are plenty others

 - 1 method that is using methods from other classes.
	FileRW => read() or write (uses objects and methods from other Classes)
	Or something from HomeActivityViewmodel or RecipeActivityViewModel
	There are plenty others
