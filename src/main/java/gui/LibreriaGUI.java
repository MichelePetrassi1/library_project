package gui;

import implementazione.Libro;
import implementazione.Libro.StatoLettura;
import implementazione.Libro.Genere;
import implementazione.LibreriaImplementazione;
import ordinamento.*;
import filtro.*;
import gui.command.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class LibreriaGUI extends JFrame {
    private final LibreriaImplementazione libreria;
    private final CommandInvoker invoker;
    private final DefaultTableModel modello;
    private final JTable tabella;
    private final JComboBox<Genere> boxGenere;
    private final JComboBox<StatoLettura> boxStato;
    private final JComboBox<Integer> boxValutazione;
    private final JTextField fieldAutore;
    private final JComboBox<String> boxOrdinamento;
    private final FiltroBuilder builder;

    public LibreriaGUI() {
        super("Gestore Libreria");
        libreria = new LibreriaImplementazione();
        invoker = new CommandInvoker();

        modello = new DefaultTableModel(new String[]{"Titolo","Autore","ISBN","Genere","Valutazione","Stato"},0);
        tabella = new JTable(modello);
        tabella.setDefaultEditor(Object.class, null); // per evitare editing sulla tabella

        // creiamo le ComboBox con la casella vuota all'inizio
        boxGenere = new JComboBox<>(createComboModelWithNull(Libro.Genere.values()));
        boxStato = new JComboBox<>(createComboModelWithNull(Libro.StatoLettura.values()));
        boxValutazione = new JComboBox<>(createComboModelWithNull(1,2,3,4,5));
        fieldAutore = new JTextField(10);
        boxOrdinamento = new JComboBox<>(new String[]{"Nessuno","Titolo","Autore","Valutazione"});

        setLayout(new BorderLayout());
        add(createTopPanel(), BorderLayout.NORTH);
        add(new JScrollPane(tabella), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        // applica il filtro vuoto all'avvio
        builder = new FiltroBuilderImpl();
        applicazioneFiltro(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900,500);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    // serve per la casella vuota
    @SafeVarargs
    private final <T> DefaultComboBoxModel<T> createComboModelWithNull(T... items) {
        Vector<T> m = new Vector<>();
        m.add(null); // essenzialmente aggiungiamo solo la null all'inizio della ComboBox
        for (T i : items) m.add(i);
        return new DefaultComboBoxModel<>(m);
    }

    // per il pannello superiore
    private JPanel createTopPanel() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Genere:")); p.add(boxGenere);
        p.add(new JLabel("Stato:")); p.add(boxStato);
        p.add(new JLabel("Valutazione:")); p.add(boxValutazione);
        p.add(new JLabel("Autore:")); p.add(fieldAutore);
        p.add(new JLabel("Ordina:")); p.add(boxOrdinamento);

        boxGenere.addActionListener(this::applicazioneFiltro);
        boxStato.addActionListener(this::applicazioneFiltro);
        boxValutazione.addActionListener(this::applicazioneFiltro);
        fieldAutore.addActionListener(this::applicazioneFiltro);
        boxOrdinamento.addActionListener(this::applicazioneOrdinamento);

        return p;
    }

    // per il pannello inferiore
    private JPanel createButtonPanel() {
        JPanel p = new JPanel();
        JButton btnAdd = new JButton("Aggiungi");
        JButton btnMod = new JButton("Modifica");
        JButton btnRem = new JButton("Rimuovi");
        JButton btnUndo = new JButton("Undo");
        JButton btnRedo = new JButton("Redo");

        btnAdd.addActionListener(this::aggiungiLibro);
        btnMod.addActionListener(this::modificaLibro);
        btnRem.addActionListener(this::rimuoviLibro);
        btnUndo.addActionListener(e -> { invoker.eseguiComando(new CommandUndo(libreria)); aggiornaTabella(); });
        btnRedo.addActionListener(e -> { invoker.eseguiComando(new CommandRedo(libreria)); aggiornaTabella(); });

        p.add(btnAdd);
        p.add(btnMod);
        p.add(btnRem);
        p.add(btnUndo);
        p.add(btnRedo);
        return p;
    }

    private void applicazioneFiltro(ActionEvent e) {
        if (boxGenere.getSelectedItem() != null)
            builder.setFiltroGenere((Libro.Genere) boxGenere.getSelectedItem());
        else builder.rimuoviFiltroGenere();
        if (boxStato.getSelectedItem() != null)
            builder.setFiltroStato((Libro.StatoLettura) boxStato.getSelectedItem());
        else builder.rimuoviFiltroStato();
        if (boxValutazione.getSelectedItem() != null)
            builder.setFiltroValutazione((Integer) boxValutazione.getSelectedItem());
        else builder.rimuoviFiltroValutazione();
        if (!fieldAutore.getText().isBlank())
            builder.setFiltroAutore(fieldAutore.getText().trim());
        else builder.rimuoviFiltroAutore();
        Filtro filtro = builder.build(); //qui, ricordo, che costruiamo il filtro product con tutti i filtri soprastanti
        invoker.eseguiComando(new CommandFiltro(libreria, filtro));
        aggiornaTabella();
    }

    private void applicazioneOrdinamento(ActionEvent e) {
        String s = (String) boxOrdinamento.getSelectedItem();
        switch (s) {
            case "Titolo":
                invoker.eseguiComando(
                        new CommandOrdinamento(libreria, new OrdinamentoPerTitolo())
                );
                break;
            case "Autore":
                invoker.eseguiComando(
                        new CommandOrdinamento(libreria, new OrdinamentoPerAutore())
                );
                break;
            case "Valutazione":
                invoker.eseguiComando(
                        new CommandOrdinamento(libreria, new OrdinamentoPerValutazione())
                );
                break;
            default:
                // nessun ordinamento: passiamo null alla strategy
                invoker.eseguiComando(
                        new CommandOrdinamento(libreria, null)
                );
                break;
        }
        aggiornaTabella();
    }

    private void aggiungiLibro(ActionEvent e) {
        JTextField titolo = new JTextField();
        JTextField autore = new JTextField();
        JComboBox<Genere> genere = new JComboBox<>(Genere.values());
        JTextField isbn = new JTextField();
        JTextField valutazione = new JTextField();
        JComboBox<StatoLettura> stato = new JComboBox<>(StatoLettura.values());

        Object[] messaggio = {
                "Titolo:", titolo,
                "Autore:", autore,
                "Genere:", genere,
                "ISBN:", isbn,
                "Valutazione:", valutazione,
                "Stato:", stato
        };
        int option = JOptionPane.showConfirmDialog(this, messaggio, "Nuovo Libro", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Libro libro = new Libro(
                    titolo.getText(),
                    autore.getText(),
                    (Libro.Genere) genere.getSelectedItem(),
                    isbn.getText(),
                    Integer.parseInt(valutazione.getText()),
                    (Libro.StatoLettura) stato.getSelectedItem()
            );
            try {
                invoker.eseguiComando(new CommandAggiungiLibro(libreria, libro));
            }catch (IllegalArgumentException h){
                JOptionPane.showMessageDialog(this, "Libro con ISBN già presente");
            }
            aggiornaTabella();
        }
    }
    private void modificaLibro(ActionEvent e) {
        int sel = tabella.getSelectedRow();
        if (sel < 0) {
            JOptionPane.showMessageDialog(this, "Seleziona un libro.");
            return;
        }
        // prendiamo l'isbn dalla colonna 2
        String isbn = modello.getValueAt(sel, 2).toString();
        // troviamo l'istanza originale
        Libro originale = libreria.getLibri().stream()
                .filter(l -> l.getCodiceISBN().equals(isbn))
                .findFirst().orElse(null);
        if (originale == null) return;

        // da qui in poi è simile ad aggiungiLibro, solo che pre-popoliamo i campi
        JTextField titolo = new JTextField(originale.getTitolo());
        JTextField autore = new JTextField(originale.getAutore());
        JTextField isbn2 = new JTextField(originale.getCodiceISBN());
        JComboBox<Genere> genereBox = new JComboBox<>(Genere.values());
        genereBox.setSelectedItem(originale.getGenere());
        JTextField valutazione = new JTextField(String.valueOf(originale.getValutazione()));
        JComboBox<StatoLettura> statoBox = new JComboBox<>(StatoLettura.values());
        statoBox.setSelectedItem(originale.getStatoLettura());

        Object[] messaggio = {
                "Titolo:", titolo,
                "Autore:", autore,
                "Genere:", genereBox,
                "ISBN:", isbn2,
                "Valutazione (1-5):", valutazione,
                "Stato:", statoBox
        };
        if (JOptionPane.showConfirmDialog(this, messaggio, "Modifica Libro", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            try {
                Libro aggiornato = new Libro(
                        titolo.getText(),
                        autore.getText(),
                        (Genere) genereBox.getSelectedItem(),
                        isbn2.getText(),
                        Integer.parseInt(valutazione.getText()),
                        (StatoLettura) statoBox.getSelectedItem()
                );
                invoker.eseguiComando(new CommandModificaLibro(libreria,originale,aggiornato));
                aggiornaTabella();
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);}
        }
    }
    private void rimuoviLibro(ActionEvent e) {
        int sel = tabella.getSelectedRow();
        if (sel < 0) {
            JOptionPane.showMessageDialog(this, "Seleziona un libro da rimuovere.");
            return;
        }
        String isbn = modello.getValueAt(sel, 2).toString();
        Libro libro = libreria.getLibri().stream()
                .filter(l -> l.getCodiceISBN().equals(isbn))
                .findFirst().orElse(null);
        if (libro != null) {
            invoker.eseguiComando(new CommandRimuoviLibro(libreria, libro));
            aggiornaTabella();
        }
    }

    private void aggiornaTabella() {
        modello.setRowCount(0);
        for (Libro l: libreria.getLibri()) {
            modello.addRow(new Object[]{l.getTitolo(),l.getAutore(),l.getCodiceISBN(),l.getGenere(),l.getValutazione(),l.getStatoLettura()});
        }
    }

}
