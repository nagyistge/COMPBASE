package arbeitsagentur.generated;

import java.math.BigInteger;

/**
 * Diese Klasse drückt  aus, dass eine Kompetenz einer anderen untergeordnet ist
 * @author julian
 *
 */
public class HierachieTriple {
	public BigInteger over;
	public BigInteger under;
	public HierachieTriple(BigInteger over, BigInteger under) {		
		this.over = over;
		this.under = under;
	}
}
