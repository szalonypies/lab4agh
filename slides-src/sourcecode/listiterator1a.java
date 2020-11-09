
List<String> lst = new ArrayList<>();
lst.addAll(Arrays.asList("marek","basia","kotek","paw"));
// remove all words containing letter a
for (String l : lst) {
    if (l.contains("a")) {
        lst.remove(l);
    }
}