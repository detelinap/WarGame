public class Card implements Comparable<Card> {
        private int strength;           // how "strong" each card is compared to the others
        private String fullName;        // how the user will see the card
        private String name;            // the name of each individual card
        private String color;           //the color of each individual card

        public Card(int strength, String name, String color) {
                this.strength = strength;
                this.name = name;
                this.color = color;
                this.fullName = name +" of "+color;
        }

        @Override
        public String toString() {
                return "Card "+ fullName ;
        }

        @Override
        public int compareTo(Card o) {
               return Integer.compare(strength, o.strength); //first card stronger = +1, first card weaker = -1, first and second card equal = 0;
        }

}

