package cn.jgb.base.java_base.jdk8.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Anmcn {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(5);
		List<Integer> taskList = Arrays.asList(1,5,8);

		CompletableFuture<Integer>[] completableFutures = taskList.stream().map(value -> CompletableFuture.supplyAsync(() -> {
			System.out.println(value);
			return value;
		},executorService)).toArray(CompletableFuture[]::new);

		//CompletableFuture.anyOf(completableFutures);
		//CompletableFuture.runAsync()
		//CompletableFuture.allOf(completableFutures);
	}
}
