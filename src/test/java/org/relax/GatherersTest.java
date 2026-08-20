package org.relax;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GatherersTest {

    @Test
    public void test1() {
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);
        Stream gathered = numbers.gather(Gatherers.fold(() -> 0, Integer::sum));
        //Stream gathered = numbers.gather(Gatherers.scan(() -> 0, Integer::sum));

        List<Integer> resultList = gathered.toList();
        assertThat(resultList).hasSize(1);
        assertThat(resultList.getFirst()).isEqualTo(Integer.valueOf(15));



        Stream<String> strings = Stream.of("pierwszy", "drugi", "trzeci");
        Stream<String> gatheredStreams = strings.gather(Gatherers.fold(() -> "paczatek", String::concat));
        List<String> stringsList = gatheredStreams.toList();
        assertThat(stringsList).hasSize(1);
    }

}
