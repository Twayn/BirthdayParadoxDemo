package com.birthday.paradox.service

import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ThreadLocalRandom

@Service
class MultiThreadGraphGenerator : GraphGenerator {
    private val numberOfPackages = 100000
    private val divisor = numberOfPackages / 100

    override fun generate(): List<GraphData> {
        return IntProgression.fromClosedRange(3, 60, 3).associateWith {
            CompletableFuture.supplyAsync { generateBirthdayPackages(numberOfPackages, it) }
                .thenApplyAsync(::calcProbability)
        }.map {
            GraphData(it.key, it.value.get())
        }
    }

    private fun calcProbability(packages: List<List<Int>>): Double {
        return packages.count {
                pack -> pack.count() != pack.distinct().count()
        }.toDouble().div(divisor)
    }

    private fun generateBirthdayPackages(numberOfPackages: Int, birthdaysInPackage: Int): List<List<Int>> {
        return List(numberOfPackages) {
            List(birthdaysInPackage) {
                ThreadLocalRandom.current().nextInt(1, 366)
            }
        }
    }
}