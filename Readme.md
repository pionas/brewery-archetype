# How generate maven project by this archetype

```
mvn archetype:generate -DarchetypeGroupId=pl.excellentapp.brewery.archetypes -DarchetypeArtifactId=brewery-archetype -DarchetypeVersion=1.0 -DinteractiveMode=false -Dname=order -DnameUppercase=Order
```

Important is `-Dname` and `-DnameUppercase`