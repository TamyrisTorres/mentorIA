/*
 * Copyright © 2025 Tamyris Torres. All rights reserved.
 *
 * This software and its associated documentation are the exclusive property of Tamyris Torres.
 *  Reproduction, distribution, modification, or unauthorized use of this system is
 *  strictly prohibited without prior written consent from the author or owner.
 *  The use of this software is granted under a limited end-user license and is subject
 * to the terms and conditions specified in the license agreement. This software is intended
 *  solely for lawful purposes and in compliance with applicable laws.
 * Any violation of these terms may result in legal penalties, including but not limited to
 * civil and criminal sanctions. For inquiries or requests, please contact: wedellatorres@gmail.com.
 */

package com.TTecnologia.mentorIA.service;

import com.TTecnologia.mentorIA.dao.FeedbackDao;
import com.TTecnologia.mentorIA.model.entity.Feedback;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    private FeedbackDao feedbackDao;

    public Feedback addFeedback(Feedback feedback) {
        return feedbackDao.save(feedback);
    }

    public Optional<Feedback> getFeedback(Integer id) {
        return feedbackDao.findById(id);
    }

    public List<Feedback> getAllFeedback() {
        return feedbackDao.findAll();
    }

    public Feedback updateFeedback(Integer id, Feedback newFeedback) {
        Optional<Feedback> feedbackOptional = getFeedback(id);

        Feedback feedbackOld = feedbackOptional.get();

        feedbackOld.setUsuario(newFeedback.getUsuario());
        feedbackOld.setRelatorioMentoria(newFeedback.getRelatorioMentoria());

        return feedbackDao.save(feedbackOld);
    }

    public void deleteFeedback(Integer id) {
        feedbackDao.deleteById(id);
    }
}

