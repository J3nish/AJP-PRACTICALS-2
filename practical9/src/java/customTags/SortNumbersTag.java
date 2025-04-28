package customtags;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class SortNumbersTag extends TagSupport {

    private String numbers; // Input numbers as a comma-separated string
    private String order; // Sorting order: "asc" or "desc"

    // Setter methods for attributes
    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public int doStartTag() throws JspException {
        if (numbers == null || numbers.trim().isEmpty()) {
            return SKIP_BODY;
        }

        String[] numArray = numbers.split(",");
        int[] intArray = new int[numArray.length];

        try {
            for (int i = 0; i < numArray.length; i++) {
                intArray[i] = Integer.parseInt(numArray[i].trim());
            }
        } catch (NumberFormatException e) {
            throw new JspException("Invalid number format! Please enter valid integers.", e);
        }

        // Debugging output
        System.out.println("Before Sorting: " + Arrays.toString(intArray));

        Arrays.sort(intArray);

        if ("desc".equalsIgnoreCase(order)) {
            for (int i = 0; i < intArray.length / 2; i++) {
                int temp = intArray[i];
                intArray[i] = intArray[intArray.length - 1 - i];
                intArray[intArray.length - 1 - i] = temp;
            }
        }

        // Debugging output
        System.out.println("After Sorting: " + Arrays.toString(intArray));

        JspWriter out = pageContext.getOut();
        try {
            out.print("<p>Sorted Numbers: " + Arrays.toString(intArray) + "</p>");
        } catch (IOException e) {
            throw new JspException("Error in SortNumbersTag", e);
        }

        return SKIP_BODY; // No body content required
    }
}
