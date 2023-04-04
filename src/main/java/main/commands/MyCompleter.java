package main.commands;

import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;
import org.jline.reader.impl.completer.StringsCompleter;

import java.util.ArrayList;
import java.util.List;

public class MyCompleter implements Completer {
    @Override
    public void complete(LineReader lineReader, ParsedLine parsedLine, List<Candidate> list) {
            String word = parsedLine.word();
            List<String> options = new ArrayList<>();
            options.add("import");
            options.add("delete");
            options.add("decrypt");
            options.add("encrypt");
            options.add("show");
            Completer  completer = new StringsCompleter(options);
            completer.complete(lineReader,parsedLine,list);
    }
}
