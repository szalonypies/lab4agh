public void addToList(List<? extends Number> lst) {
  Number item1 = lst.get(0); // ok
  lst.add(3); // error
}

List<Float> floatList = ...;
addToList(floatList);
