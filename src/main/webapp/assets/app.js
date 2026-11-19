document.addEventListener("DOMContentLoaded", () => {
  document.querySelectorAll("form").forEach((form) => {
    form.addEventListener("submit", (event) => {
      const title = form.querySelector('[name="title"]');
      const description = form.querySelector('[name="description"]');
      if (title && title.value.trim().length < 3) {
        event.preventDefault();
        alert("Please enter a title with at least 3 characters.");
      } else if (description && description.value.trim().length < 15) {
        event.preventDefault();
        alert("Please enter a description with at least 15 characters.");
      }
    });
  });
});
