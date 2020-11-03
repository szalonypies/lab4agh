public void addToList(List<? super Integer>) {
  Integer i = lst.get(0); // error
  lst.add(4); // ok
}

List<Number> numberList = ...;
addToList(numberList);
