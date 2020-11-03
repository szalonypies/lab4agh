public void addToList(List<? extends Number> lst) {
  Number item1 = lst.get(0);
  lst.add(3);
}

List<Float> floatList = ...;
addToList(floatList);
