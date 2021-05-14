## Google Guava

### Guava新增集合类型-Multimap

Multimap的实现:

Multimap提供了丰富的实现，所以你可以用它来替代程序里的Map<K, Collection<V>>，具体的实现如下：
Implementation            Keys 的行为类似       　　　    Values的行为类似
ArrayListMultimap           HashMap                   　　ArrayList
HashMultimap                HashMap                  　　 HashSet
LinkedListMultimap          LinkedHashMap*               LinkedList*
LinkedHashMultimap          LinkedHashMap                LinkedHashSet
TreeMultimap                TreeMap                      TreeSet
ImmutableListMultimap       ImmutableMap                 ImmutableList
ImmutableSetMultimap        ImmutableMap                 ImmutableSet
