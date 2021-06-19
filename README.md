# AladdinDB
</br>
<hr>

## The magically passionate relationship 🥰

Dear friends, 

**AladdinDB** is a completely new, purely **Object-Oriented Database-System for Java** applications. 

Knowing that **AladdinDB** will be one of the best **Object-Oriented Database-System for Java**, I spend my days and nights incessantly developing it ;-) 

**AladdinDB** isn't all about access times and security.
It is also about to be able to **Juggle Around with Data in a Free and Playful Way**.

I hope that programming database applications with **Aladdin will be a lot more fun**.

</br>
<hr>

## Three-dimensional data structures :electron:

Equivalent to **Data-Records** in classic **Relational-Database-Systems**, so-called `Units` in **AladdinDB** form the smallest, separately stored data units. 

In contrast to **Data-Records**, `Units` can map three-dimensional data structures. 

Such structures are described in **AladdinDB**, purely **Object-Oriented**, using `DataModel` classes and saved as `XML` documents.

</br>

> Here is an example of a `Unit`:

```xml
<Unit version="1.0" id="1560700320d1b71b220">
    <Meta>
        <UnitLabel/>
        <TimeStamp generatedOn="2021-06-07T00:50:13.454866+03:00[Europe/Istanbul]" modifiedOn="2021-06-07T00:50:13.454866+03:00[Europe/Istanbul]"/>
    </Meta>
    <Person>
        <Name first="Sergius" last="Redlich"/>
        <Birth day="2016-02-23"/>
        <Address street="Westernoher Straße" postCode="44145" houseNumber="76" city="Dortmund"/>
        <Contact telephone="0231/91708900" mobile="0150/3229958">
            <EmailList>
                <Email type="private"/>
            </EmailList>
        </Contact>
    </Person>
</Unit>
```

</br>
<hr>

## As free as a bird 🕊️

`Units` of the same `DataModel` types have their origin in corresponding **repositoris**.

`Stores` are completely independent storage units and can be distributed across any storage media and locations at the same time.

In **embedded** systems you can easily access "stores" via `Store` objects. 

Each `Store` object is its own **Micro-Database-System**.

</br>

> An example for a embedded store access:

```java
var store = new Store<>( Person.class,  Paths.get("/home/macit/tmp/aladdin/db/persons") );

store.search( finder, sorter, unit -> { 
...
});

store.addUnits(
	new Person("Mike","Stone"),
	new Person("Aylin","Dilara"),
	new Person("Noah","Elijah")
);

store.forEachUnit( unit -> {
...
});

...
```
</br>
<hr>

## Genie 🧞 and the Magic-Lamp 🪔

For **Remote Access**, each `Store` object receives its instructions directly from its own `Genie` 🧞 object. 

Each `Genie` 🧞 object is in turn its own **Micro-Database-Server** and can be used freely and distributed.

On the other hand, on the client a `MagicLamp` 🪔 object is the counterpart to every `Genie` 🧞. 

In other words, every **Genie 🧞** gets his instructions directly from a **Magic-Lamp 🪔** 😄

</br>

> An example for starting a **AladdinDB** Server:

```java
Genie<Book> books = Genie.newGenie( Book.class, "books", Paths.get( "/home/macit/tmp/aladdin/db/books" ),

	unit->unit.data.get().title,
	unit->unit.data.get().author,
	unit->unit.data.get().firstPublication
);

var genieInvoker = new GenieInvoker( 7735 );

genieInvoker.putGenie( books );

genieInvoker.invoke();
```

</br>

> And here, an example for the client side:

```java
MagicLamp<Book> books = new MagicLamp<>( Book.class, "books", new GenieConnection( "localhost", 7735 ) );

books.search( ... );

```
</br>
<hr>

## Embedded ? Distributed ? or rather Both ? 🤔 

This means that **AladdinDB** is ideally usable as an **Embedded-System** with simultaneous **Remote Accessing** to distributed systems as a **hybrid** solution.

The developer will hardly notice the difference, since he moves in his usual **Object-Oriented Java Environment** during the entire development time.

</br>
<hr>

## Playful search 🤹 

In **AladdinDB**, `search` processes use easy-to-use, developer-supplied `Finder` objects. 

For each property to be searched a separate `Finder` class can be created.

`LogicalFinder` enable nested logical links between `Finder` objects. 

`Sorter` objects that work according to the same principle exist for the simultaneous sorting of the result sets. 

**Complex queries can be implemented very easily, clearly and elegantly**

</br>

> An example:

```java
var fs = new FinderSupport<>( Book.class );
var ss = new SorterSupport<>( Book.class );

var book = new Book();

var finders = fs.newAndFinders(
	fs.newFinder			( "matches"	, "Lord.*"			,book.title ),
	fs.newDateFinder		( ">="		, LocalDate.of( 2005, 1, 1 )	,book.firstPublication ),
	fs.newZondedDateTimeFinder	( ">"		, Unit.dateTime( 2021, 6, 17 )	,Unit.MODIFIED_ON() )
);

var sorters = ss.newSorterList( ss.newSorter( book.firstPublication ));

MagicLamp<Book> books = new MagicLamp<>( Book.class, "books", new GenieConnection( "localhost", 7735 ), fs, ss );

books.searchAndGetUnitsNavi( 50 , finders, sorters , navi -> {
...
});

```

## Aladdin's navigation system

`Search` processes get a `blockSize` value and return a `BlockNaviResp` object as a result.
 
This object contains the first **UnitId block** with the maximum number of **UnitId's** specified in `blockSize`.

It also contains the information as to whether **previous and/or next UnitId blocks exist**.

Encapsulated in a so-called `BlockNavi` object, it is now very easy to **Backward and Forward Server-Side-Navigation** over the finded **UnitId blocks**.

> See above example

</br>
<hr>

## Magical variables 🧚

An approach to **Object-Oriented Programming** that I developed makes **Data Modeling with AladdinDB much easier and clearer**. 

I'm talking about so-called `Var<>` objects with generic content. 

Such objects encapsulate and handle access to their content through their own **getter and setter methods**. 

Class attributes of data models therefore will no longer require **getter and setter**. 

Above all, `Var<>` objects offer additional methods for functional access to his content through **lambda expressions** and also accept `Var<>` objects for setting his own content. 

With this technique I was able to solve some automations in AladdinDB much more elegantly.

</br>

> An example for a `PersonModel`:

```java
public class Person extends DefaultDataModel < Person > {

	public final Var < String > title = new Var<>( this, null );
	
	public final Name	name 		= new Name	( this );
	public final Birth	birth  		= new Birth	( this );
	public final Address	address 	= new Address	( this );
	public final Contact	contact 	= new Contact	( this );
	
	public Person() {
		super( null );
	}
	
	public Person( String title, String firstName, String lastName, LocalDate birthDay ) {
		super( null );
		
		this.title	.set( title	);
		this.name.first	.set( firstName );
		this.name.last	.set( lastName	);
		this.birth.day	.set( birthDay	);
	}
	
	@Override
	public void fill( Person store ) {
		
		this.title	.set( store.title 	);
		
		this.name	.fill( store.name 	);
		this.birth	.fill( store.birth	);
		this.address	.fill( store.address	);
		this.contact	.fill( store.contact	);
	}
	
}

```
> And here for the `PersonModel`'s `address` field, which is itself a `DataModel`:

```java
public class Address extends DefaultDataModel < Address > {

	public final Var < String 	> street 	= new Var<>( this, null );
	public final Var < Integer 	> postCode 	= new Var<>( this, null );
	public final Var < String	> houseNumber 	= new Var<>( this, null );
	public final Var < String	> city 		= new Var<>( this, null );
	
	public Address( Parent parent ) {
		super( parent );
	}
	
	@Override
	public void fill ( Address model ) {
		this.street		.set( model.street 	);
		this.houseNumber	.set( model.houseNumber );
		this.postCode		.set( model.postCode 	);
		this.city		.set( model.city 	);
	}
	
}

```


