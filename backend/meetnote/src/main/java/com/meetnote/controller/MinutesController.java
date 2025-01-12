package com.meetnote.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MinutesController {

    private List<Minutes> minutesList = new ArrayList<>();

    // コンストラクタでモックデータを初期化
    public MinutesController() {
        minutesList.add(new Minutes(1, "定例会議", "2025-01-11", "議題1: 進捗報告\n議題2: 次回のタスク"));
        minutesList.add(new Minutes(2, "プロジェクト会議", "2025-01-12", "議題1: 新機能の仕様検討\n議題2: バグ修正"));
    }
    

    @GetMapping("/minutes")
    public List<Minutes> getAllMinutes() {
        return minutesList;
    }

    @GetMapping("/minutes/{id}")
    public Minutes getMinutesById(@PathVariable int id) {
        Optional<Minutes> minutes = minutesList.stream()
                .filter(m -> m.id == id)
                .findFirst();

        if (minutes.isPresent()) {
            return minutes.get();
        } else {
            throw new RuntimeException("議事録が見つかりません: ID=" + id);
        }
    }

    @PostMapping("/minutes")
    public ResponseEntity<Minutes> createMinutes(@RequestBody Minutes newMinutes) {
        int newId = minutesList.size() + 1;
        newMinutes.setId(newId);
        minutesList.add(newMinutes); // 新規データをリストに追加
        return ResponseEntity.status(HttpStatus.CREATED).body(newMinutes); // 新規データをレスポンスとして返す
    }

    
    @PutMapping("/minutes/{id}")
    public Minutes updateMinutes(@PathVariable int id, @RequestBody Minutes updatedMinutes) {
        Optional<Minutes> existingMinutes = minutesList.stream()
                .filter(m -> m.getId() == id)
                .findFirst();

        if (existingMinutes.isPresent()) {
            Minutes minutes = existingMinutes.get();
            minutes.setTitle(updatedMinutes.getTitle());
            minutes.setDate(updatedMinutes.getDate());
            minutes.setContent(updatedMinutes.getContent());
            return minutes;
        } else {
            throw new RuntimeException("議事録が見つかりません: ID=" + id);
        }
    }
    
    @DeleteMapping("/minutes/{id}")
    public ResponseEntity<Void> deleteMinutes(@PathVariable int id) {
        Optional<Minutes> minutesToDelete = minutesList.stream()
                .filter(m -> m.getId() == id)
                .findFirst();

        if (minutesToDelete.isPresent()) {
            minutesList.remove(minutesToDelete.get());
            return ResponseEntity.noContent().build(); // ステータス204を返す
        } else {
            throw new RuntimeException("議事録が見つかりません: ID=" + id);
        }
    }


    static class Minutes {
        private int id;
        private String title;
        private String date;
        private String content;

        // コンストラクタ
        public Minutes(int id, String title, String date, String content) {
            this.id = id;
            this.title = title;
            this.date = date;
            this.content = content;
        }

        // GetterとSetter
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}

