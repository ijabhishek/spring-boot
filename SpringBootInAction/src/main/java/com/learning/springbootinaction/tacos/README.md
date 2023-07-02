## DesignTacoController

The first thing to note about DesignTacoController is the set of annotations applied at the class level. The first, `@Slf4j`, is a Lombok-provided annotation that, at compilation time, will automatically generate an SLF4J (Simple Logging Facade for Java, [https://www.slf4j.org/](https://www.slf4j.org/)) Logger static property in the class. This modest annotation has the same effect as if you were to explicitly add the following lines within the class:

```java
private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);
```

You’ll make use of this Logger a little later.

The next annotation applied to DesignTacoController is `@Controller`. This annotation serves to identify this class as a controller and to mark it as a candidate for component scanning, so that Spring will discover it and automatically create an instance of DesignTacoController as a bean in the Spring application context.

DesignTacoController is also annotated with `@RequestMapping`. The `@RequestMapping` annotation, when applied at the class level, specifies the kind of requests that this controller handles. In this case, it specifies that DesignTacoController will handle requests whose path begins with `/design`.

Finally, you see that DesignTacoController is annotated with `@SessionAttributes("tacoOrder")`. This indicates that the TacoOrder object that is put into the model a little later in the class should be maintained in session. This is important because the creation of a taco is also the first step in creating an order, and the order we create will need to be carried in the session so that it can span multiple requests.

### HANDLING A GET REQUEST

The class-level `@RequestMapping` specification is refined with the `@GetMapping` annotation that adorns the `showDesignForm()` method. `@GetMapping`, paired with the class-level `@RequestMapping`, specifies that when an HTTP GET request is received for `/design`, Spring MVC will call `showDesignForm()` to handle the request.

`@GetMapping` is just one member of a family of request-mapping annotations. Table 2.1 lists all of the request-mapping annotations available in Spring MVC.

When `showDesignForm()` handles a GET request for `/design`, it doesn’t really do much. The main thing it does is return a String value of "design", which is the logical name of the view that will be used to render the model to the browser. But before it does that, it also populates the given Model with an empty Taco object under a key whose name is "design". This will enable the form to have a blank slate on which to create a taco masterpiece.

It would seem that a GET request to `/design` doesn’t do much. But on the contrary, there’s a bit more involved than what is found in the `showDesignForm()` method.

You’ll also notice a method named `addIngredientsToModel()` that is annotated with `@ModelAttribute`. This method will also be invoked when a request is handled and will construct a list of Ingredient objects to be put into the model. The list is hardcoded for now. When we get to chapter 3, you’ll pull the list of available taco ingredients from a database.

Once the list of ingredients is ready, the next few lines of `addIngredientsToModel()` filters the list by ingredient type using a helper method named `filterByType()`. A list of ingredient types is then added as an attribute to the Model object that will be passed into `showDesignForm()`. Model is an object that ferries data between a controller and whatever view is charged with rendering that data. Ultimately, data that

’s placed in Model attributes is copied into the servlet request attributes, where the view can find them and use them to render a page in the user’s browser.

Table 2.1 Spring MVC request-mapping annotations

| Annotation       | Description                      |
|------------------|----------------------------------|
| @RequestMapping  | General-purpose request handling  |
| @GetMapping      | Handles HTTP GET requests         |
| @PostMapping     | Handles HTTP POST requests        |
| @PutMapping      | Handles HTTP PUT requests         |
| @DeleteMapping   | Handles HTTP DELETE requests      |
| @PatchMapping    | Handles HTTP PATCH requests       |

Following `addIngredientsToModel()` are two more methods that are also annotated with `@ModelAttribute`. These methods are much simpler and create only a new TacoOrder and Taco object to place into the model. The TacoOrder object, referred to earlier in the `@SessionAttributes` annotation, holds state for the order being built as the user creates tacos across multiple requests. The Taco object is placed into the model so that the view rendered in response to the GET request for `/design` will have a non-null object to display.

Your DesignTacoController is really starting to take shape. If you were to run the application now and point your browser at the `/design` path, the DesignTacoController’s `showDesignForm()` and `addIngredientsToModel()` would be engaged, placing ingredients and an empty Taco into the model before passing the request on to the view. But because you haven’t defined the view yet, the request would take a horrible turn, resulting in an HTTP 500 (Internal Server Error) error. To fix that, let’s switch our attention to the view where the data will be decorated with HTML to be presented in the user’s web browser.