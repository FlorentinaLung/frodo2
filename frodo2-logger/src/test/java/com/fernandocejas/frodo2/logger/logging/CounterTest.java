package com.fernandocejas.frodo2.logger.logging;

import com.fernandocejas.frodo2.test.UnitTest;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CounterTest extends UnitTest {

  private static final String COUNTER_NAME = "android10";

  private Counter counter;

  @Before
  public void setUp() {
    counter = new Counter(COUNTER_NAME);
  }

  @Test
  public void incrementsCounter() {
    counter.increment();
    counter.increment();
    counter.increment();

    assertThat(counter.tally()).isEqualTo(3);
  }

  @Test
  public void clearsCounter() {
    counter.increment();
    counter.increment();
    assertThat(counter.tally()).isEqualTo(2);

    counter.clear();
    assertThat(counter.tally()).isZero();
  }

  @Test
  public void decrementsCounter() {
    counter.increment();
    counter.increment();
    counter.increment();
    counter.decrement();
    counter.decrement();

    assertThat(counter.tally()).isEqualTo(1);
  }

  @Test
  public void comparesCounterValues() {
    final Counter anotherCounter = new Counter("myCounter");

    counter.increment();
    counter.increment();
    assertThat(counter.compareTo(anotherCounter)).isEqualTo(1);

    anotherCounter.increment();
    anotherCounter.increment();
    assertThat(counter.compareTo(anotherCounter)).isZero();

    counter.decrement();
    assertThat(counter.compareTo(anotherCounter)).isEqualTo(-1);
  }

  @Test
  public void countsNegatives() {
    counter.decrement();
    counter.decrement();

    assertThat(counter.tally()).isEqualTo(-2);
  }
}
