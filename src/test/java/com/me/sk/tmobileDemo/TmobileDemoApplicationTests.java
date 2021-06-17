package com.me.sk.tmobileDemo;

import com.me.sk.tmobileDemo.controller.MainController;
import com.me.sk.tmobileDemo.model.NumValue;
import com.me.sk.tmobileDemo.model.error.GenericException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

@SpringBootTest
class TmobileDemoApplicationTests {
	private static final Map<String, Integer> addTenTestData = new HashMap<>();
	private static final Map<Integer, Integer> addOpTestData = new HashMap<>();
	private static final Random rand = new Random(-134560);
	static {
		Stream.iterate(-20, n -> n + 5).limit(20).forEach(v -> addTenTestData.put(Integer.toString(v), v + 10));
		Stream.iterate(0,n -> n+1).limit(20).forEach(v -> {rand.setSeed(v*1369493021); addOpTestData.put(rand.nextInt(), rand.nextInt());});
	}
	@Autowired
	private MainController controller;

	@Test
	void contextLoads() {
	}

	@Test
	void testAddTenNormal(){
		addTenTestData.entrySet().stream().parallel().forEach(e -> {
			NumValue value = controller.doAddTenOp(e.getKey());
			Assertions.assertEquals(value.getValue(), (int) e.getValue(), "Add Ten - testing value: " + e.getKey() + "" +
					" expected: " + e.getValue() + ", but found: " + value.getValue());
		});
	}

	@Test
	void testAddTenNormalFail(){
		Assertions.assertThrows(GenericException.class, () -> controller.doAddTenOp(""), "Add Ten - Fail testing expected exception.");
		Assertions.assertThrows(GenericException.class, () -> controller.doAddTenOp("Test"), "Add Ten - Fail testing expected exception.");
		Assertions.assertThrows(GenericException.class, () -> controller.doAddTenOp("12Test"), "Add Ten - Fail testing expected exception.");
	}


	@Test
	void testAddOpNormal(){
		addOpTestData.entrySet().stream().parallel().forEach(e -> {
			NumValue value = controller.doAddOp(Integer.toString(e.getKey()), Integer.toString(e.getValue()));
			long addition = e.getKey() + e.getValue();
			Assertions.assertEquals(value.getValue(), addition, "Add Operation - Testing value: " + e + ", Found: " + value + ", Expected: " + addition);
		});
	}

	@Test
	void testAddOpNormalFail(){
		Assertions.assertThrows(GenericException.class, () -> controller.doAddOp("", "123"), "Add Operation - Fail testing expected exception.");
		Assertions.assertThrows(GenericException.class, () -> controller.doAddOp("", "Test"), "Add Operation - Fail testing expected exception.");
		Assertions.assertThrows(GenericException.class, () -> controller.doAddOp("12Test", "123"), "Add Operation - Fail testing expected exception.");
		Assertions.assertThrows(GenericException.class, () -> controller.doAddOp("123", "Test"), "Add Operation - Fail testing expected exception.");
		Assertions.assertThrows(GenericException.class, () -> controller.doAddOp("123", ""), "Add Operation - Fail testing expected exception.");
		Assertions.assertThrows(GenericException.class, () -> controller.doAddOp("12", "123Test"), "Add Operation - Fail testing expected exception.");
	}
}
