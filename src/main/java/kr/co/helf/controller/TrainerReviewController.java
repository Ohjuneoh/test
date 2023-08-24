package kr.co.helf.controller;

import kr.co.helf.dto.TrainerReviewDto;
import kr.co.helf.form.AddReviewForm;
import kr.co.helf.form.ModifyReviewForm;
import kr.co.helf.service.TrainerReviewService;
import kr.co.helf.vo.TrainerReview;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/trainer-review")
@RequiredArgsConstructor
@Slf4j
public class TrainerReviewController {

    private final TrainerReviewService trainerReviewService;
    // 트레이너 리뷰 리스트 화면 출력 - 후에 강사소개 페이지에서 값 전달 되면 트레이너 번호 받아서 출력해야 함.
    @GetMapping("/list")
    public String reviewList(@RequestParam("trainerNo") int trainerNo, Model model){

        return "/trainer-review/list";
    }
    // 트레이너 리뷰 등록 화면
    @GetMapping("/registration")
    public String reviewForm(){
        return "/trainer-review/form";
    }
    // 트레이너 리뷰 등록
    @PostMapping("/registration")
    public String review(AddReviewForm form){
        trainerReviewService.createReview(form);
        return "trainer-review/list";
    }
    // 트레이너 리뷰 수정 화면
    @GetMapping("/modify")
    public String reviewModify(int reviewNo,Model model){
        TrainerReview review = trainerReviewService.getReviewByNo(reviewNo);
        model.addAttribute("review",review);

        return "trainer-review/modifyForm";
    }
    // 리뷰 수정 로직
    @PostMapping("/modify")
    public String reviewModifyForm(int trainerNo,ModifyReviewForm form){
        trainerReviewService.updateReview(form);

        return "redirect:/trainer-review/list?trainerNo="+trainerNo;
    }

    // 리뷰 삭제 로직
    @GetMapping("/delete")
    public String deleteReview(@RequestParam("reviewNo") int reviewNo){
        trainerReviewService.deleteReview(reviewNo);
        return "redirect:/trainerIntro";
    }
}
