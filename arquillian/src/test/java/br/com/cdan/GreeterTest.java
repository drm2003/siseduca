package br.com.cdan;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class GreeterTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class).addClass(Greeter.class).addAsManifestResource(EmptyAsset.INSTANCE,
				"beans.xml");
	}

	@Test
	public void should_create_greeting() {
		Assert.fail("Not yet implemented");
	}
}