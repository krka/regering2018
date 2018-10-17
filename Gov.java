import java.util.stream.IntStream;

public class Gov {
  public static void main(String[] args) {
    // Source of data: http://www.riksdagen.se/sv/valet-2018/den-nya-riksdagen-efter-valet/
    String[] parties = {"S", "M", "SD", "C", "V", "KD", "L", "MP"};
    int[] mandates = {
        100, 70, 62, 31, 28, 22, 20, 16
    };

    // Pairs of parties that don't want to be in the same government.
    int[] filters = {
      convert(parties, "S", "SD"),
      convert(parties, "C", "SD"),
      convert(parties, "L", "SD"),
      convert(parties, "V", "SD"),
      convert(parties, "MP", "SD"),

      convert(parties, "V", "M"),
      convert(parties, "V", "KD"),

      convert(parties, "S", "M"),
      convert(parties, "S", "KD"),
    };

    // Sanity check numbers
    if (349 != IntStream.of(mandates).sum()) {
      throw new RuntimeException();
    }

    for (int theSet = 0; theSet <= 0b11111111; theSet++) {
      int sum = 0;
      for (int party = 0; party < 8; party++) {
        if (hasParty(theSet, party)) {
          sum += mandates[party];
        }
      }
      // Need both a majority and no conflicts
      if (sum >= 175 && acceptable(filters, theSet)) {
        StringBuilder sb = new StringBuilder();
        for (int party = 0; party < 8; party++) {
          if (hasParty(theSet, party)) {
            sb.append(parties[party]).append(" ");
          }
        }
        System.out.println(sum + ": " + sb.toString());
      }
    }
  }

  private static boolean acceptable(final int[] filters, final int theSet) {
    for (int filter : filters) {
      if ((filter & theSet) == filter) {
        return false;
      }
    }
    return true;
  }

  private static int convert(final String[] parties, final String a, final String b) {
    int filter = 0;
    for (int i = 0; i < 8; i++) {
      if (parties[i].equals(a)) {
        filter |= (1 << i);
      }
      if (parties[i].equals(b)) {
        filter |= (1 << i);
      }
    }
    return filter;
  }

  private static boolean hasParty(final int theSet, final int party) {
    return (theSet & (1 << party)) != 0;
  }

}

