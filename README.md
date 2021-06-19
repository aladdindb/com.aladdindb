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

>Here is an example of a `Unit`

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

>An example for a embedded store access

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

In other words, every **Genie 🧞** gets his instructions directly from a **Magic-Lamp 🪔** ;-)


> An example for starting a **AladdinDB** Server

```java
		var personGenie = Genie.newGenie( Person.class, "persons", Paths.get( "/home/macit/tmp/aladdin/db/persons" ), 
				
			unit -> unit.data.get().name.first, 
			unit -> unit.data.get().name.last, 
			
			unit -> unit.data.get().address.city, 
			unit -> unit.data.get().address.houseNumber, 
			unit -> unit.data.get().address.postCode,
			unit -> unit.data.get().address.street,
			
			unit -> unit.data.get().birth.city, 
			unit -> unit.data.get().birth.country, 
			unit -> unit.data.get().birth.day,
			
			unit -> unit.data.get().contact.mobile,
			unit -> unit.data.get().contact.telephone
		);

		var genieInvoker = new GenieInvoker( 7735 );
		
		genieInvoker.putGenie( personGenie );
		
		genieInvoker.invoke();

```
