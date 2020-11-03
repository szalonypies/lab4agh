public void addToList(List<? super Integer>) {
  Integer i = lst.get(0);
  lst.add(4);
}

List<Number> numberList = ...;
addToList(numberList);
